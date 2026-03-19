FROM php:8.2-apache

RUN a2enmod rewrite

RUN docker-php-ext-install mysqli

WORKDIR /var/www/html
COPY . /var/www/html/

RUN printf '%s\n' \
'<VirtualHost *:80>' \
'    DocumentRoot /var/www/html/backend' \
'    <Directory /var/www/html/backend>' \
'        Options Indexes FollowSymLinks' \
'        AllowOverride All' \
'        Require all granted' \
'        DirectoryIndex index.php index.html' \
'    </Directory>' \
'</VirtualHost>' \
> /etc/apache2/sites-available/000-default.conf

RUN chown -R www-data:www-data /var/www/html

EXPOSE 80
CMD ["apache2-foreground"]